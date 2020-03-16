import { IFormData } from 'app/shared/model/form-data.model';

export interface IFormSchema {
  id?: string;
  definition?: string;
  formData?: IFormData[];
}

export class FormSchema implements IFormSchema {
  constructor(public id?: string, public definition?: string, public formData?: IFormData[]) {}
}
