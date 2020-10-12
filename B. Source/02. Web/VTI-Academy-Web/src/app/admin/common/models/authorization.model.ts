export class AuthorizationModel {
  success: boolean;
  token: string;
  message: string;
  role: string;
  name: string;
  username: string;

  constructor(model: any = null) {
    if (model) {
      this.success = model.success;
      this.token = model.token;
      this.message = model.message;
      this.role = model.role;
      this.name = model.name;
      this.username = model.username;
    }
  }

  hasRole(role: string) {
    return this.role === role
  }
}
