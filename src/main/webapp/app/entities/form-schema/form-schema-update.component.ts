import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFormSchema, FormSchema } from 'app/shared/model/form-schema.model';
import { FormSchemaService } from './form-schema.service';

@Component({
  selector: 'jhi-form-schema-update',
  templateUrl: './form-schema-update.component.html'
})
export class FormSchemaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    definition: []
  });

  constructor(protected formSchemaService: FormSchemaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formSchema }) => {
      this.updateForm(formSchema);
    });
  }

  updateForm(formSchema: IFormSchema): void {
    this.editForm.patchValue({
      id: formSchema.id,
      definition: formSchema.definition
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formSchema = this.createFromForm();
    if (formSchema.id !== undefined) {
      this.subscribeToSaveResponse(this.formSchemaService.update(formSchema));
    } else {
      this.subscribeToSaveResponse(this.formSchemaService.create(formSchema));
    }
  }

  private createFromForm(): IFormSchema {
    return {
      ...new FormSchema(),
      id: this.editForm.get(['id'])!.value,
      definition: this.editForm.get(['definition'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormSchema>>): void {
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
}
