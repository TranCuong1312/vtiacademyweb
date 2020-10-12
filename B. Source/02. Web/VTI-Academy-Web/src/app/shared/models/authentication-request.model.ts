export class AuthenticationRequest{
    username: string;
    password: string;
    constructor(params){
        this.username  = params.username;
        this.password = params.password;
    }
}