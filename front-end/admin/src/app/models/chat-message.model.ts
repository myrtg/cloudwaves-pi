import { User } from "./user";

export interface ChatMessage {
    id: string;
    chatId: number;
    recipient: User;
    sender: User;
    content: string;
    timestamp: Date;
    recipientUserName:string;
  }