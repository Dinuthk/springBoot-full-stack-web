import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';


import { HeaderComponent } from './components/header/header.component';
import { ContentComponent } from './components/content/content.component';

@Component({
  selector: 'app-root',
  standalone: true,  // Make this component standalone as well
  imports: [RouterOutlet, HeaderComponent, ContentComponent], // Import HeaderComponent here
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';
  name = 'dinuth';
}
