import {inject, Injectable} from '@angular/core'
import {HttpClient, HttpHeaders} from '@angular/common/http'
import {Router} from '@angular/router'
import {backendURL} from "../../environment/environment";
import {ToastrService} from "ngx-toastr";
import {MessageService} from "primeng/api";

interface Token {
  token: string
}


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly baseUrl = backendURL + "/user"
  private http = inject(HttpClient)
  private router = inject(Router)
  private messageService: MessageService = inject(MessageService)

  get username(): string | null {
    return localStorage.getItem('username')
  }

  set username(username: string | null | undefined) {
    if (username == null) localStorage.removeItem('username')
    else localStorage.setItem('username', username)
  }

  get isLoggedIn(): boolean {
    return this.authToken != null
  }

  get authToken(): string | null {
    return localStorage.getItem('token')  // TODO: httpOnly cookie
  }

  set authToken(token: string | null | undefined) {
    if (token == null) localStorage.removeItem('token')
    else localStorage.setItem('token', token)
  }

  private auth(username: string, token: string) {
    this.authToken = token
    this.username = username
    this.router.navigate(['main'])
  }

  login(username: string, password: string) {

    return this.http
      .post<Token>(`${this.baseUrl}/login`, {username, password}, {headers: this.httpHeaders})
      .subscribe({
        next: data => this.auth(username, data.token),
        error: error => {
          this.showError(error);
        }
      })
  }

  register(username: string, password: string) {
    return this.http
      .post<Token>(`${this.baseUrl}/register`, {username, password}, {headers: this.httpHeaders})
      .subscribe({
        next: data => this.auth(username, data.token),
        error: error => {
          this.showError(error);
        }
      })
  }

  private showError(message: any) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: message
      })
    }

  get httpHeaders() {
    let headers = new HttpHeaders();
    headers.set('Authorization', `Basic ${this.authToken}`)
    return headers;
  }

  logout() {
    this.authToken = null
    this.username = undefined
    this.router.navigate(['login'])
  }
}
