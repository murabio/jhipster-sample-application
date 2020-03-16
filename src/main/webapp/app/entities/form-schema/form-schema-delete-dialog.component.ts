import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormSchema } from 'app/shared/model/form-schema.model';
import { FormSchemaService } from './form-schema.service';

@Component({
  templateUrl: './form-schema-delete-dialog.component.html'
})
export class FormSchemaDeleteDialogComponent {
  formSchema?: IFormSchema;

  constructor(
    protected formSchemaService: FormSchemaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.formSchemaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('formSchemaListModification');
      this.activeModal.close();
    });
  }
}
