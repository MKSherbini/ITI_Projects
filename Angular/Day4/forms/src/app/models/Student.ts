import { Serializable } from "./Serializable";

export class StudentType {
  public FirstName?: string = "firstName";
  public LastName?: string = "lastName";
  public Name?: string = "name";
  public Mobile?: string = "mobile";
  public Email?: string = "email";
  public CreatedDate?: string = "date";
  public ID?: number = -1;
  public Age?: number = 18;
  public IsDeleting?: boolean = false;
}

export class Student extends StudentType implements Serializable<Student> {

  // constructor(public FirstName?: string,
  //   public LastName?: string,
  //   public Age?: number,
  //   public Mobile?: string,
  //   public Email?: string,
  //   public CreatedDate?: string,
  //   public ID?: string,
  //   public Name?: string,
  // ) {
  //   console.log("Employee created");

  //   this.FirstName = FirstName || "sponge";
  //   this.LastName = LastName || "bob";
  //   this.Age = Age || 18;
  //   this.Mobile = Mobile || "666666666";
  //   this.Email = Email || "Email";
  // }
  constructor(s: StudentType = {}) {
    super();
    for (const key in s) {
      if (Object.prototype.hasOwnProperty.call(s, key)) {
        const element = s[key] || this[key];
        this[key] = element;
      }
    }
  }

  deserialize(s) {
    for (const key in s) {
      if (Object.prototype.hasOwnProperty.call(s, key)) {
        const element = s[key];
        this[key] = element;
      }
    }
    return this;
  }

  public getFullName(): string {
    return this.FirstName + " " + this.LastName;
  }

  toString(): string {
    return JSON.stringify(this);
  }
}

