import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { DataServiceService } from './data-service.service';
import { Laptop } from './laptop';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  idToUpdate: number | undefined;
  update: Boolean = false;

  title = 'laptop-store-application';
  url: string = 'http://localhost:8080/laptops';

  constructor(private formBuilder: FormBuilder, private http: HttpClient, private service: DataServiceService) {}

  laptops: Laptop[] = [];

  myForm = this.formBuilder.group({
    fname:  ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
    fprice: ['', [Validators.required, Validators.max(9999), Validators.min(0)]],
    fbrand: ['', [Validators.required]],
    fstorage: ['', [Validators.required]],
    fram: ['', [Validators.required]],
    fprocessor: ['', [Validators.required]],
    sname: ['', ],
    sprice: ['', ],
    sbrand: ['', ]
  })

  updateForm = this.formBuilder.group({
    uname:  ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
    uprice: ['', [Validators.required, Validators.max(9999), Validators.min(0)]],
    ubrand: ['', [Validators.required]],
    ustorage: ['', [Validators.required]],
    uram: ['', [Validators.required]],
    uprocessor: ['', [Validators.required]],
  })

  ngOnInit() {
    this.handleSearch()
  }

  get fname() {
   return this.myForm.get('fname') as FormControl; 
  }

  get fprice() {
    return this.myForm.get('fprice') as FormControl; 
  }

  get fbrand() {
    return this.myForm.get('fbrand') as FormControl; 
  }

  get fstorage() {
  return this.myForm.get('fstorage') as FormControl; 
  }

  get fram() {
  return this.myForm.get('fram') as FormControl; 
  }

  get fprocessor() {
  return this.myForm.get('fprocessor') as FormControl; 
  }

  //For update form
  get uname() {
    return this.updateForm.get('uname') as FormControl; 
   }
 
   get uprice() {
     return this.updateForm.get('uprice') as FormControl; 
   }
 
   get ubrand() {
     return this.updateForm.get('ubrand') as FormControl; 
   }
 
   get ustorage() {
   return this.updateForm.get('ustorage') as FormControl; 
   }
 
   get uram() {
   return this.updateForm.get('uram') as FormControl; 
   }
 
   get uprocessor() {
   return this.updateForm.get('uprocessor') as FormControl; 
   }
  onSubmit() {
    let name: string = this.myForm?.controls['fname'].value!;
    let price: number = parseInt(this.myForm?.controls['fprice'].value!, 10);
    let brand: string = this.myForm?.controls['fbrand'].value!;
    let ram: number = parseInt(this.myForm?.controls['fram'].value!, 10);
    let processor: string = this.myForm?.controls['fprocessor'].value!;
    let storage: number = parseInt(this.myForm?.controls['fstorage'].value!, 10);

    let laptop = new Laptop(name, price, brand, ram, processor, storage);
    this.service.addLaptop(laptop).subscribe(
      (data) => {
        console.log(data)
        this.handleSearch()
      }
    )
  }

  onSubmitUpdate() {
    let name: string = this.updateForm?.controls['uname'].value!;
    let price: number = parseInt(this.updateForm?.controls['uprice'].value!, 10);
    let brand: string = this.updateForm?.controls['ubrand'].value!;
    let ram: number = parseInt(this.updateForm?.controls['uram'].value!, 10);
    let processor: string = this.updateForm?.controls['uprocessor'].value!;
    let storage: number = parseInt(this.updateForm?.controls['ustorage'].value!, 10);
  
    let laptop = new Laptop(name, price, brand, ram, processor, storage);
    this.service.edit(this.idToUpdate!, laptop).subscribe(
      (data) => {
        console.log(data)
        this.handleSearch()
      }
    )
    this.update = !this.update;
  }

  getLaptops() {
    this.service.findAllLaptops().subscribe(
      data => {
        this.laptops = data;
      }, 
      error => {
        console.log(error)
      }
    )
  }

  onDelete(id: number) {
    this.service.delete(id).subscribe(
      data => {
        console.log(data);
        this.handleSearch()
      }, 
      err => {
        console.log(err);
      }
    )
  }

  onEdit(id: number) {
    this.idToUpdate = id;
    // let name: string = this.myForm?.controls['fname'].value!;
    // let price: number = parseInt(this.myForm?.controls['fprice'].value!, 10);
    // let brand: string = this.myForm?.controls['fbrand'].value!;
    // let ram: number = parseInt(this.myForm?.controls['fram'].value!, 10);
    // let processor: string = this.myForm?.controls['fprocessor'].value!;
    // let storage: number = parseInt(this.myForm?.controls['fstorage'].value!, 10);

    // let laptop = new Laptop(name, price, brand, ram, processor, storage);
    // this.service.edit(id, laptop).subscribe(
    //   (data) => {
    //     console.log(data)
    //     this.handleSearch()
    //   }
    // )
    this.update = !this.update;
  }
    

  handleSearch() {
    const searchedName = this.myForm?.controls['sname'].value;
    const searchedPrice = this.myForm?.controls['sprice'].value;
    const searchedBrand = this.myForm?.controls['sbrand'].value;

    this.service.search(searchedName!, searchedPrice!, searchedBrand!).subscribe(
      (data) => {
        this.laptops = data;
      },
      err => {
        console.log(err)
      }
    )}
}
