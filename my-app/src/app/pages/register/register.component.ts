import {Component, inject} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {UserService} from "../../service/user.service";
import {MessageService} from "primeng/api";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule,
    HttpClientModule,
    RouterLink
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  form: any = {
    'username': null,
    'password': null,
    'passwordConfirmation': null
  }

  private http: HttpClient = inject(HttpClient)
  private userService: UserService = inject(UserService)
  private messageService = inject(MessageService)

  onSubmit() {
    if (this.form.username == null || this.form.username.length < 6 || this.form.username.length > 24) {
      this.showError('Логин от 6 до 24 символов')
      return;
    }
    if (this.form.password != this.form.passwordConfirmation) {
      this.showError('Пароли не совпадают')
      return;
    }
    if (this.form.password == null || this.form.password.length < 6 || this.form.password.length > 24) {
      this.showError('Пароль от 6 до 24 символов')
      return
    }

    this.userService.register(this.form.username, this.form.password)

  }

  private showError(message: string) {
    this.messageService.add({
      severity: 'error',
      summary: 'Error',
      detail: message
    })
  }

}
