import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { TokenStorageService } from '../helpers/token-storage.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertModalService } from '../../shared/alert-modal.service';
import { SocialAuthService } from "angularx-social-login";
import { FacebookLoginProvider, GoogleLoginProvider, VKLoginProvider } from "angularx-social-login";
import { SocialUser } from "angularx-social-login";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = this.formBuilder.group({});
  loading = false;
  submitted = false;
  isLoginFailed = false;
  roles: string[] = [];

  socialUser?: SocialUser;
  loggedIn= false;
  
  constructor (
    private formBuilder: FormBuilder,
    private router: Router,
    private modal: AlertModalService,
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private socialAuthService: SocialAuthService
  ) { }

  ngOnInit(): void {

    this.socialAuthService.authState.subscribe((socialUser) => {
      this.socialUser = socialUser;
      this.loggedIn = (socialUser != null);
    });

    this.clearForm();
    
    if (this.tokenStorage.getToken()) {
      this.loggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  onSubmit(): void {
    
    this.submitted = true;
    
    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
   
    this.authService.login(this.f.email.value, this.f.senha.value).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token || '');
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        this.loggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
      },
      err => {
        this.isLoginFailed = true;
        this.clearForm();
        this.modal.showAlertDanger(err.error.message);
      }
    );
  }
 
  private clearForm(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      senha: ['', Validators.required],
      rememberme: [''],
      captcha: ['', Validators.required]
    });
    this.loading = false;
    this.submitted = false;
  }

  public goRegister(): void {
    this.router.navigate(['/register']);
  }

  public goForgetPassword(): void {
    this.router.navigate(['/forgetpassword']);
  }

  private reloadPage(): void {
    window.location.reload();
  }

  public signInWithGoogle(): void {
    this.socialAuthService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }

  public signInWithFB(): void {
    this.socialAuthService.signIn(FacebookLoginProvider.PROVIDER_ID);
  }
}