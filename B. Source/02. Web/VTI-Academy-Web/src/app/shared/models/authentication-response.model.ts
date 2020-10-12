export class AuthenticationResponse {
    authenticationToken: string;
    username: string;
    role : string;
    refreshToken : string;
    constructor(params){
        this.authenticationToken  = params.authenticationToken;
        this.username = params.username;
        this.role = params.role;
        this.refreshToken = params.refreshToken;
    }
}