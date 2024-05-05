export interface MessageResponse {
  messageId: number;
  senderId: number;
  receiverId: number;
  message: string;
  timestamp: Date;
  readStatus: boolean; // Add this property

  // notificationMessage: string;

}
