/* tslint:disable */
import { User } from "src/app/models/user";

/* eslint-disable */
export interface AuthenticationResponse {
  token?: string;
  user?: User;
  mfaEnabled ?:   string;
  secretImageUri?: string;
}
