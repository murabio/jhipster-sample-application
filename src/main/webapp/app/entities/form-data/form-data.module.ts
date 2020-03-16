import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { FormDataComponent } from './form-data.component';
import { FormDataDetailComponent } from './form-data-detail.component';
import { FormDataUpdateComponent } from './form-data-update.component';
import { FormDataDeleteDialogComponent } from './form-data-delete-dialog.component';
import { formDataRoute } from './form-data.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(formDataRoute)],
  declarations: [FormDataComponent, FormDataDetailComponent, FormDataUpdateComponent, FormDataDeleteDialogComponent],
  entryComponents: [FormDataDeleteDialogComponent]
})
export class JhipsterSampleApplicationFormDataModule {}
