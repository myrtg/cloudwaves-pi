import { Injectable } from '@angular/core';
import { Client, Message } from '@stomp/stompjs';


@Injectable({
  providedIn: 'root',
})
export class StompService {
  //url = "http://localhost:8080/stomp-endpoint";
  //stompClient = Stomp.client(this.url);
  public stompClient: Client;

  constructor() {
    // Initialize StompClient and configure debugging
    this.stompClient = new Client();
    // Connect to the WebSocket server
    this.initializeStompClient();
  }

  private initializeStompClient() {
    const serverUrl = 'ws://localhost:8080/stomp-endpoint'; // WebSocket URL

    this.stompClient.configure({
      brokerURL: serverUrl,
      // Other configuration options if needed
    });

    this.stompClient.activate();
    this.stompClient.debug = (message: any) => { console.log(message); };

    // // Connect to the WebSocket server
    // this.stompClient.connect({}, () => {
    //   // Successful connection callback
    //   console.log('Stomp Client Connected');
    // }, (error: any) => {
    //   // Error callback
    //   console.error('Stomp Client Connection Error:', error);
    // });
  }

  subscribe(topic: string, callback: any) {
    return this.stompClient.subscribe('/topic/' + topic, (frame: any): any => {
      const message = JSON.parse(frame.body);
      callback(message);
      // Set user online when subscribing to the channel
      // Notify other users about the new user's presence
      this.notifyNewUserPresence(message.userId); 
    });
  }
  notifyNewUserPresence(userId: string) {
    // Send a message to a topic notifying other users about the new user's presence
    this.stompClient.publish({
      destination: '/topic/userPresence',
      body: JSON.stringify({ userId, presence: 'online' }), 
    });
  }

  send(app: string, data: any) {
    this.stompClient.publish({
      destination: `/app/${app}`,
      body: JSON.stringify( data ),
    });
  }
}
