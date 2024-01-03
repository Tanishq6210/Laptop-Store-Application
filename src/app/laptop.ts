export class Laptop {
    id!: number;
    constructor(
    public name: string,
    public price: number,
    public brand: string,
    public ram: number,
    public processor: string,
    public storage: number
    ) {}
}