import {Component, inject} from '@angular/core';
import {UserService} from "../../service/user.service";
import {NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import { ToastModule } from 'primeng/toast';
@Component({
  selector: 'app-index',
  standalone: true,
  imports: [
    NgIf,
    RouterLink,
  ],
  templateUrl: './index.component.html',
  styleUrl: './index.component.scss'
})
export class IndexComponent {
  protected userService = inject(UserService)
}
