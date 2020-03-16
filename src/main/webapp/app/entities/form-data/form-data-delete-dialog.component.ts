import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormData } from 'app/shared/model/form-data.model';
import { FormDataService } from './form-data.service';

@Component({
  templateUrl: './form-data-delete-dialog.component.html'
})
export class FormDataDeleteDialogComponent {
  formData?: IFormData;

  constructor(protected formDataService: FormDataService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.formDataService.delete(id).subscribe(() => {
      this.eventManager.broadcast('formDataListModification');
      this.activeModal.close();
    });
  }
}
