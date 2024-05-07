import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  constructor() {
    // Create a WebSocket connection
    const socket = new WebSocket('ws://localhost:8081/stomp-endpoint');

    // Add event listeners for WebSocket events
    socket.onopen = function(event) {
      console.log('WebSocket connection opened.');
    };

    socket.onmessage = function(event) {
      console.log('Received message:', event.data);
    };

    socket.onerror = function(error) {
      console.error('WebSocket error:', error);
    };

    socket.onclose = function(event) {
      console.log('WebSocket connection closed.');
    };
  }
}
