import {CanActivateFn, Router} from "@angular/router";
import {inject} from "@angular/core";

export const authGuard: CanActivateFn = (route, state) => {
  if (localStorage.getItem("token")) return true
  inject(Router).navigate(['login'])
  return true
}

export const loginGuard: CanActivateFn = (route, state) => {
  if (localStorage.getItem("token")) return false
  inject(Router).navigate(['main'])
  return true
}
