import { ChatMessage } from "./chat-message.model";

export interface User {
    id: number;
    username: string;
    nickname:string;
    fullName: string;
    status: Status;
    sentMessages?: ChatMessage[];
    receivedMessages?: ChatMessage[];
  }
  
  export enum Status {
    ONLINE = 'ONLINE',
    OFFLINE = 'OFFLINE',
    AWAY = 'AWAY'
  }
  