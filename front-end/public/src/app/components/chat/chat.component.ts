import { Component, OnInit } from '@angular/core';
import { ChatMessage } from '../../models/chat-message.model';
import { User } from '../../models/user';
import { ChatService } from 'src/app/chat.service';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  chatMessages: ChatMessage[] = [];
  connectedUsers: User[]= [];
  selectedUserId: string | null = null; 
  currentUser: string| null = null;
  constructor(private chatService: ChatService,private userService: UserService) {}

  ngOnInit(): void {
    //this.fetchChatMessages('senderId', 'recipientId'); 
    this.loadConnectedUsers();
    this.currentUser=localStorage.getItem("nickname");

  }
  loadConnectedUsers(): void {
    this.userService.getConnectedUsers().subscribe(
      (users) => {
        console.log("loadConnectedUsers", users, this.currentUser)
        this.connectedUsers = users.filter(u => u.nickname!=this.currentUser);
      },
      (error) => {
        console.error('Error loading connected users:', error);
      }
    );
  }
  fetchChatMessages(senderId: string, recipientId: string): void {

    this.chatService.getChatMessages(senderId, recipientId)
      .subscribe(
        (messages: ChatMessage[]) => {
          console.log("messages ", messages)
          this.chatMessages = messages;
        },
        (error) => {
          console.error('Error fetching chat messages:', error);
        }
      );
  }

    // Method to handle user selection
  selectUser(userId: string): void {
    this.selectedUserId = userId;
    console.log('selected user', this.selectedUserId)
    this.fetchChatMessages(String(this.currentUser), this.selectedUserId); 
  }
}
