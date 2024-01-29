import {Component, inject} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserService} from "../../service/user.service";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  form: any = {
    'username': '',
    'password': ''
  }

  private http: HttpClient = inject(HttpClient)
  private us: UserService = inject(UserService)

  onSubmit() {
    this.us.login(this.form.username, this.form.password)
  }
}
