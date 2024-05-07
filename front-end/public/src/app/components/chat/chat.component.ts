import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { MessageRequest } from '../../interfaces/message-request';
import { ApiResponse } from 'src/app/interfaces/api-response';
import { User } from 'src/app/interfaces/user';
import { ConversationResponse } from 'src/app/interfaces/conversation-response';
import { MessageResponse } from 'src/app/interfaces/message-response';
import { StompService } from 'src/app/services/stomp.service';
import { Subscription } from 'rxjs';
import { WebSocketResponse } from 'src/app/interfaces/web-socket-response';
import { StompSubscription } from '@stomp/stompjs';
import { ChatService } from 'src/app/services/chat.service';
import{connected_users} from '../../global';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit, OnDestroy {
  currentUser: User = {
    id: 0,
    firstName: '',
    lastName: '',
    email: '',
    online:true,
  };
  // all users except current user
  users: User[] = [];
  // users all conversations
  userConversations: ConversationResponse[] = [];
  // current user conversation subscription
  //stompUserSub: Subscription | undefined;
  //private stompUserSub: Subscription | null = null;
  stompUserSub!: StompSubscription;
  stompConvSub!: StompSubscription;
  // selected conversation
  selectedConversationId: number = -1;
  selectedConversationReceiverId: number = -1;
  selectedConversationReceiverName: string = '';
  selectedUserlastname!: string;
  // selected conversation messages
  selectedConversation: MessageResponse[] = [];
  // selected conversation messages subscription
  //stompConvSub: Subscription | undefined;

  // Boolean flag to indicate whether showing users or conversation on left column
  showUserState: boolean = false;
  // Input field for send message
  message: string = '';
  roomId!: number;
  receiverUserId!: number;
  constructor(
    private router: Router,
    private userService: UserService,
    private chatService: ChatService,
    private stomp: StompService
  ) {
    this.currentUser = userService.currentUser();
  }

  ngOnInit(): void {
    // Subscribe to id websocket to get updated conversation when gets new messages
    this.subscribeToCurrentUserConversation();
    this.getAllUsers();
    this.filterMessages(); // Add this line to initialize filteredMessages
    this.subscribeToUserPresence();
  }
  subscribeToUserPresence() {
    this.stomp.subscribe(
      'userPresence',
      (payload: any) => {
        const { userId, presence } = payload;
        // Update the online status of the user
        const user = this.users.find(u => u.id === userId);
        if (user) {
          user.online = presence === 'online';
        }
      }
    );
  }
  // Déclarez la propriété filteredUsers dans votre composant
filteredUsers: User[] = [];
userSearchQuery: string = '';

// Créez une méthode pour filtrer les utilisateurs en fonction de la requête de recherche
filterUsers() {
    if (!this.userSearchQuery) {
        // Si la requête de recherche sur les utilisateurs est vide, affichez tous les utilisateurs
        this.filteredUsers = this.users;
    } else {
        // Filtrer les utilisateurs en fonction de la requête de recherche
        const lowerCaseQuery = this.userSearchQuery.toLowerCase();
        this.filteredUsers = this.users.filter(user =>
            (user.firstName.toLowerCase().includes(lowerCaseQuery) || user.lastName.toLowerCase().includes(lowerCaseQuery))
        );
    }
}

// Appelez cette méthode lorsque la requête de recherche sur les utilisateurs change
onUserSearchQueryChange() {
    this.filterUsers();
}

  ngOnDestroy(): void {
    // Unsubscribe from all channels onDestroy
    //this.stompUserSub?.unsubscribe();
    this.stompConvSub?.unsubscribe();
  }
  searchUsers() {
    if (!this.userSearchQuery) {
        // Si la requête de recherche est vide, affichez tous les utilisateurs
        this.filteredUsers = this.users;
    } else {
        // Filtrer les utilisateurs en fonction de la requête de recherche
        const lowerCaseQuery = this.userSearchQuery.toLowerCase();
        this.filteredUsers = this.users.filter(user =>
            user.firstName.toLowerCase().includes(lowerCaseQuery) ||
            user.lastName.toLowerCase().includes(lowerCaseQuery)
        );
    }
}

  // When click the new/add button Then get all users and set users list
  
  getAllUsers() {
    this.userService
    .getAllUsersExceptCurrentUser()
    .subscribe((res: ApiResponse) => {
      this.users = res.data;
      console.log('connected users', this.users)

    });

  }
  // Créez une méthode pour filtrer les messages avant de les envoyer
filterMessage(message: string): string {
  // Vérifiez si le message contient des mots indésirables
  for (const badWord of this.badWords) {
      if (message.toLowerCase().includes(badWord)) {
          // Si un mot indésirable est trouvé, remplacez-le par des astérisques
          message = message.replace(new RegExp(badWord, 'ig'), '*'.repeat(badWord.length));
      }
  }
  // Retournez le message filtré
  return message;
}

  // Close a chat from dropdown menu
  onCloseChat() {
    this.stompConvSub?.unsubscribe();
    this.selectedConversationId = -1;
  }
  badWords: string[] = ['test', 'test2', 'test3'];

  // When click logout button Then remove user from localStorage and navigate to homepage
  onUserLogout() {
    localStorage.removeItem('user');
    this.router.navigate(['/']);
  }

  subscribeToCurrentUserConversation() {
    // setting one second delayed to successfully connect the stomp to server
    setTimeout(() => {
      this.stompUserSub = this.stomp.subscribe(
        'user/' + this.currentUser.id,
        (payload: any) => {
          let res: WebSocketResponse = payload;
          if (res.type == 'ALL') {
            this.userConversations = res.data;
            const found = this.userConversations.find(
              (item) => item.conversationId === this.selectedConversationId
            );
            if (found === undefined) {
              this.onCloseChat();
            }
          }
        }
      );
      // Notify that I'm subscribed to get initial data
      this.stomp.send('user', this.currentUser.id);
    }, 1000);
  }



  // When new or exiting user selected Then set the variables and get the two users
  // conversationId from the database
  onUserSelected(receiverId: number, receiverName: string,recieverlastname:string) {
    this.selectedConversationReceiverId = receiverId;
    this.selectedConversationReceiverName = receiverName;
    this.selectedUserlastname= recieverlastname;
    this.userService
      .getConversationIdByUser1IdAndUser2Id(receiverId, this.currentUser.id)
      .subscribe((res: ApiResponse) => {
        console.log('getConversationIdByUser1IdAndUser2Id', res.data)
        this.selectedConversationId = res.data;
        this.getAllUsers();
        this.setConversation();
      });
  }

  // When user select a conversation from the list
  onConversationSelected(index: number) {
    this.selectedConversationId = this.userConversations[index].conversationId;
    this.selectedConversationReceiverId =
      this.userConversations[index].otherUserId;
    this.selectedConversationReceiverName =
      this.userConversations[index].otherUserName;

    this.setConversation();
    this.markMessagesAsRead( this.selectedConversationId ,  this.selectedConversationReceiverId)
  }

  // Set a conversation of selected conversationId
  setConversation() {
    // unsubscribe any previous subscription
    this.stompConvSub?.unsubscribe();
    // then subscribe to selected conversation
    // when get new message then add the message to first of the array
    this.stompConvSub = this.stomp.subscribe(
      'conv/' + this.selectedConversationId,
      (payload: any) => {
        let res: WebSocketResponse = payload;
        if (res.type == 'ALL') {
          this.selectedConversation = res.data;
          this.sortMessages()
        } else if (res.type == 'ADDED') {
          let msg: MessageResponse = res.data;
          this.selectedConversation.unshift(msg);
          this.sortMessages()
        }
      }
    );
    // Notify that I'm subscribed to get initial data
    this.stomp.send('conv', this.selectedConversationId);
  }
  sortMessages(){
    this.selectedConversation.sort((a,b)=>{
      const dt1 =a.timestamp;
      const dt2 = b.timestamp;
  
      if (dt1 > dt2) return 1;
      if (dt1 < dt2) return -1;
      return 0;
  });
  }
  markMessagesAsRead(room:number, receiverId:number): void {
    this.chatService.markMessagesAsRead(room, receiverId);
  }
  // Send message to other user
onSendMessage() {
  // If message field is empty then return
  if (this.message.trim().length == 0) return;

  // Filtrer le message avant de l'envoyer
  const filteredMessage = this.filterMessage(this.message.trim());

  const timestamp = new Date();
  let body: MessageRequest = {
      conversationId: this.selectedConversationId,
      senderId: this.userService.currentUser().id,
      receiverId: this.selectedConversationReceiverId,
      message: filteredMessage, // Utiliser le message filtré
      timestamp: timestamp,
  };

  console.log("body ", body);
  this.stomp.send('sendMessage', body);
  this.message = '';
}


  // When click Delete chat from the dropdown menu Then delete the conversation
  // with it's all messages
  onDeleteConversation() {
    this.stomp.send('deleteConversation', {
      conversationId: this.selectedConversationId,
      user1Id: this.currentUser.id,
      user2Id: this.selectedConversationReceiverId,
    });
    window.location.reload();
  }
  searchQuery: string = '';

  filteredMessages: MessageResponse[] = [];

  filterMessages() {
    console.log('Filtering messages...');

    if (!this.searchQuery) {
        console.log('Search query is empty. Showing all messages.');
        // If the search query is empty, show all messages in the selected conversation
        this.filteredMessages = this.selectedConversation;
    } else {
        console.log('Search query:', this.searchQuery);
        const lowerCaseQuery = this.searchQuery.toLowerCase();
        this.filteredMessages = this.selectedConversation.filter(message =>
            message.message.toLowerCase().includes(lowerCaseQuery)
        );
    }

    console.log('Filtered messages:', this.filteredMessages);
}

onSearchQueryChange() {
  this.filterMessages();
}
  // When click delete on a message menu Then delete from database Then refresh
  // conversation list
  onDeleteMessage(messageId: number) {
    this.stomp.send('deleteMessage', {
      conversationId: this.selectedConversationId,
      messageId: messageId,
    });
  }
}