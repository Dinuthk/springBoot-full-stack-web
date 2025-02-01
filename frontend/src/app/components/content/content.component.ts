import { Component } from '@angular/core';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MenuService } from '../../service/menu.service';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-content',
  standalone: true,
  imports: [MatSidenavModule,MatListModule,MatIconModule,RouterModule],
  templateUrl: './content.component.html',
  styleUrl: './content.component.scss'
})
export class ContentComponent {
  opened = true;

  constructor( private menuService: MenuService) { 
    this.menuService.isOpende.subscribe(data => this.opened = data);
  }
}
