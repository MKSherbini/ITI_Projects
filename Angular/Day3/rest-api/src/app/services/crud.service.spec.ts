import { TestBed } from '@angular/core/testing';
import { StudentResponse } from '../models/StudentResponse';
import { CrudService } from './crud.service';


describe('StudentCrudService', () => {
  let service: CrudService<StudentResponse>;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CrudService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
