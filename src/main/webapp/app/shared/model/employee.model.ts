import { Moment } from 'moment';
import { IJob } from 'app/shared/model/job.model';
import { IFormData } from 'app/shared/model/form-data.model';
import { IDepartment } from 'app/shared/model/department.model';

export interface IEmployee {
  id?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  phoneNumber?: string;
  hireDate?: Moment;
  salary?: number;
  commissionPct?: number;
  jobs?: IJob[];
  formData?: IFormData[];
  manager?: IEmployee;
  department?: IDepartment;
}

export class Employee implements IEmployee {
  constructor(
    public id?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public phoneNumber?: string,
    public hireDate?: Moment,
    public salary?: number,
    public commissionPct?: number,
    public jobs?: IJob[],
    public formData?: IFormData[],
    public manager?: IEmployee,
    public department?: IDepartment
  ) {}
}
