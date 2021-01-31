import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from './auth/auth.service';
import { Usuario } from './shared/models/usuario';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    currentUser: Usuario = new Usuario();
    currentUserId?: number;

    constructor(
        private router: Router,
        private authService: AuthService
    ) {
        this.authService.currentUser.subscribe(x => this.currentUser =  x);

    }

    

    public logout(): void {
        this.authService.logout();
        this.router.navigate(['/auth']);
    }
}
