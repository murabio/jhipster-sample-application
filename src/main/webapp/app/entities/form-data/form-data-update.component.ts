import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFormData, FormData } from 'app/shared/model/form-data.model';
import { FormDataService } from './form-data.service';
import { IEmployee } from 'app/shared/model/employee.model';
import { EmployeeService } from 'app/entities/employee/employee.service';
import { IFormSchema } from 'app/shared/model/form-schema.model';
import { FormSchemaService } from 'app/entities/form-schema/form-schema.service';

type SelectableEntity = IEmployee | IFormSchema;

@Component({
  selector: 'jhi-form-data-update',
  templateUrl: './form-data-update.component.html'
})
export class FormDataUpdateComponent implements OnInit {
  isSaving = false;
  employees: IEmployee[] = [];
  formschemas: IFormSchema[] = [];

  editForm = this.fb.group({
    id: [],
    payload: [],
    author: [],
    schema: []
  });

  constructor(
    protected formDataService: FormDataService,
    protected employeeService: EmployeeService,
    protected formSchemaService: FormSchemaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formData }) => {
      this.updateForm(formData);

      this.employeeService.query().subscribe((res: HttpResponse<IEmployee[]>) => (this.employees = res.body || []));

      this.formSchemaService.query().subscribe((res: HttpResponse<IFormSchema[]>) => (this.formschemas = res.body || []));
    });
  }

  updateForm(formData: IFormData): void {
    this.editForm.patchValue({
      id: formData.id,
      payload: formData.payload,
      author: formData.author,
      schema: formData.schema
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formData = this.createFromForm();
    if (formData.id !== undefined) {
      this.subscribeToSaveResponse(this.formDataService.update(formData));
    } else {
      this.subscribeToSaveResponse(this.formDataService.create(formData));
    }
  }

  private createFromForm(): IFormData {
    return {
      ...new FormData(),
      id: this.editForm.get(['id'])!.value,
      payload: this.editForm.get(['payload'])!.value,
      author: this.editForm.get(['author'])!.value,
      schema: this.editForm.get(['schema'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormData>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
