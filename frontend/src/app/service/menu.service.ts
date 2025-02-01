import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor() { }

  public isOpende = new BehaviorSubject<boolean>(false);
  public opended = false;

  public toggleMenu() {
    this.opended = !this.opended;
    this.isOpende.next(this.opended);
  }
}
