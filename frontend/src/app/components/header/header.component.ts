import { Component } from '@angular/core';
import { MatSlideToggleModule } from '@angular/material/slide-toggle'; // Import MatSlideToggleModule
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MenuService } from '../../service/menu.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [MatSlideToggleModule, MatButtonModule,MatDividerModule, MatIconModule,MatToolbarModule],  // Include MatSlideToggleModule in imports
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  constructor(private menuService: MenuService) {
  }
  toggleMenu() {
    this.menuService.toggleMenu();
  }
}
