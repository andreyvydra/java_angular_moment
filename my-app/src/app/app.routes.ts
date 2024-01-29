import {CanActivateFn, Router, Routes} from '@angular/router';
import {RegisterComponent} from "./pages/register/register.component";
import {LoginComponent} from "./pages/login/login.component";
import {MainComponent} from "./pages/main/main.component";
import {IndexComponent} from "./pages/index/index.component";
import {inject} from "@angular/core";
import {UserService} from "./service/user.service";

const authGuard: CanActivateFn = (route, state) => {
  if (inject(UserService).isLoggedIn) return true
  inject(Router).navigate(['login'])
  return true
}

const unauthGuard: CanActivateFn = (route, state) => {
  if (!inject(UserService).isLoggedIn) return true
  inject(Router).navigate(['main'])
  return true
}

export const routes: Routes = [
  {path: 'register', component: RegisterComponent, canActivate: [unauthGuard]},
  {path: 'login', component: LoginComponent, canActivate: [unauthGuard]},
  {path: 'main', component: MainComponent, canActivate: [authGuard]},
  {path: '', component: IndexComponent},
  {path: '**', redirectTo: ''}
];
