import { Routes } from '@angular/router';
import { CarComponent } from './components/car/car.component';
import { AdminComponent } from './components/admin/admin.component';
import { ContentComponent } from './components/content/content.component';
import { ForbiddenComponent } from './components/forbidden/forbidden.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { UserComponent } from './components/user/user.component';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
    {path: 'car',component: CarComponent},
    {path: 'admin',component: AdminComponent},
    {path: 'content',component: ContentComponent},
    {path: 'forbidden',component: ForbiddenComponent},
    {path: 'header',component: HeaderComponent},
    {path: 'home',component: HomeComponent},
    {path: 'user',component: UserComponent},
    
    {path: 'login',component: LoginComponent},
];
