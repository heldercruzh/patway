import { HttpClient, HttpHeaders } from '@angular/common/http';
import { take } from 'rxjs/operators';
import { ReturnJson } from '../shared/return-json';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { GenericModel } from './generic.model';
import { BehaviorSubject } from 'rxjs';
import { Usuario } from '../shared/models/usuario';





/*
  The "Generic Service" is a class created to provide a default service
  to make easily to create a services for eath object that need
*/
export class GenericService<T> {
  constructor(protected http: HttpClient, private serviceEndpoint: string) {

  }
  
  protected currentUserSubject: BehaviorSubject<Usuario> = new BehaviorSubject<Usuario>( new Usuario);
  public currentUser: Observable<Usuario> = new Observable<Usuario>();

  // store user details and jwt token in local storage to keep user logged in between page refreshes
  protected initToken(usuario: Usuario): void {
    localStorage.setItem(usuario.token, JSON.stringify(usuario));
    this.currentUserSubject.next(usuario);
    this.currentUser = this.currentUserSubject.asObservable();
  }

  protected clearToken(): void {
     // remove user from local storage to log user out
     localStorage.removeItem('');
     this.currentUserSubject.next(new Usuario);
     this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): Usuario {
    return this.currentUserSubject.value;
  }



  public remove(id: number): Observable<any> {
      return this.http.delete<ReturnJson>(
        `${environment.apiUrl}/${this.serviceEndpoint}/${id}`).pipe(take(1));
  }
  
  public read(id?: number): Observable<any>{
    let url = `${environment.apiUrl}/${this.serviceEndpoint}`;
    if (id) {
      url += `/${id}`;
    }
    return this.http.get<any>(url);
  }

  public save(record: GenericModel): Observable<any> {

    if (record['id'] == null || record['id'] == 0) {
      const options = {headers: new HttpHeaders().set('Content-Type', 'application/json')};        
      return this.http.post<ReturnJson>( `${environment.apiUrl}/${this.serviceEndpoint}`, record, options).pipe(take(1));
    } else {
      return this.http.put<ReturnJson>(`${environment.apiUrl}/${this.serviceEndpoint}/${record['id']}`, record);
    }
    
  }
}
