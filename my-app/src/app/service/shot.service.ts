import {HttpClient, HttpHeaders, HttpRequest} from "@angular/common/http";
import {inject, Injectable} from "@angular/core";
import {backendURL} from "../../environment/environment";
import {UserService} from "./user.service";

export interface Shot {
  id: number,
  x: number,
  y: number,
  r: number,
  inArea: boolean,
  shotTime: string
}

@Injectable({
  providedIn: 'root'
})
export class ShotService {
  private readonly baseUrl = `${backendURL}/shots`
  private http = inject(HttpClient)
  private user = inject(UserService)

  retrieveShots() {
    let header = {
      headers: new HttpHeaders()
        .set('Authorization',  `Basic ${this.user.authToken}`)
    }
    return this.http.get<Shot[]>(this.baseUrl, header)
  }

  createShot(x: number | string, y: number | string, r: number | string) {
    let header = {
      headers: new HttpHeaders()
        .set('Authorization',  `Basic ${this.user.authToken}`)
    }
    return this.http.post<Shot>(this.baseUrl, {x, y, r}, header)
  }
}
