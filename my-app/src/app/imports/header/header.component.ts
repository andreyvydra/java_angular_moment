import {Component} from "@angular/core";
import {RouterLink} from "@angular/router";
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',
  styleUrl: 'header.component.scss',
  imports: [
    RouterLink,
    NgOptimizedImage
  ],
  standalone: true
})
export class HeaderComponent {
  title = 'header';
}
