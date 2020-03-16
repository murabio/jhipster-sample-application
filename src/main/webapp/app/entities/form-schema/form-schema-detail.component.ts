import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormSchema } from 'app/shared/model/form-schema.model';

@Component({
  selector: 'jhi-form-schema-detail',
  templateUrl: './form-schema-detail.component.html'
})
export class FormSchemaDetailComponent implements OnInit {
  formSchema: IFormSchema | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formSchema }) => (this.formSchema = formSchema));
  }

  previousState(): void {
    window.history.back();
  }
}
