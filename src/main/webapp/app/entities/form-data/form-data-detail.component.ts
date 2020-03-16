import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormData } from 'app/shared/model/form-data.model';

@Component({
  selector: 'jhi-form-data-detail',
  templateUrl: './form-data-detail.component.html'
})
export class FormDataDetailComponent implements OnInit {
  formData: IFormData | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formData }) => (this.formData = formData));
  }

  previousState(): void {
    window.history.back();
  }
}
