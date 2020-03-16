import { IEmployee } from 'app/shared/model/employee.model';
import { IFormSchema } from 'app/shared/model/form-schema.model';

export interface IFormData {
  id?: string;
  payload?: string;
  author?: IEmployee;
  schema?: IFormSchema;
}

export class FormData implements IFormData {
  constructor(public id?: string, public payload?: string, public author?: IEmployee, public schema?: IFormSchema) {}
}
