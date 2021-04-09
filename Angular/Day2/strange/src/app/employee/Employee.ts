
export class Employee {
  username: string;
  firstName: string = "sponge";
  lastName: any = "bob";
  age: number = 18;

  constructor(obj: any) {
    console.log("Employee created");

    this.firstName = obj && obj.firstName || "sponge";
    this.lastName = obj && obj.lastName || "bob";
    this.age = obj && obj.age || 18;
  }

  public getFullName(): string {
    return this.firstName + " " + this.lastName;
  }

  // toString(): string {
  //   return "sisi";
  // }
}
