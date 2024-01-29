import {ApplicationConfig, inject} from '@angular/core';
import {provideRouter} from '@angular/router';

import { routes } from './app.routes';
import {MessageService} from "primeng/api";
import {HttpInterceptorFn, provideHttpClient, withInterceptors} from "@angular/common/http";
import {provideAnimations} from "@angular/platform-browser/animations";
import {UserService} from "./service/user.service";

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(),
    provideAnimations(),
    MessageService
  ]
}
