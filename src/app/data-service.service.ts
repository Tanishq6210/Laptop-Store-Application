import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Laptop } from './laptop';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  constructor(private http: HttpClient) { }
  url: string = 'http://localhost:8080/laptops';


  addLaptop(laptop: Laptop): Observable<Laptop> {
    return this.http.post<Laptop>(`${this.url}`, laptop);
  }

  findAllLaptops(): Observable<Laptop[]> {
    return this.http.get<Laptop[]>(`${this.url}`);
  }

  delete(id: number) {
    return this.http.delete(this.url + '/' + id, {responseType : 'text'});
  }

  edit(id: number, laptop: Laptop) {
    return this.http.put(`${this.url}/${id}`, laptop, {responseType : 'text'});
  }

  search(sname: string, sprice: string, sbrand: string): Observable<Laptop[]> {
    let qparams: any = {};
    if(sname.length > 0) {
      qparams['name'] = sname;
    }

    if(sbrand.length > 0) {
      qparams['brand'] = sbrand;
    }

    if(sprice.length > 0) {
      qparams['price'] = parseInt(sprice, 10);
    }

    return this.http.get<Laptop[]>(`${this.url}/search`, {params: qparams});
  }
}
