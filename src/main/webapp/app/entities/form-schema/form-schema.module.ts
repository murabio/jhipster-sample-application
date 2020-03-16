import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { FormSchemaComponent } from './form-schema.component';
import { FormSchemaDetailComponent } from './form-schema-detail.component';
import { FormSchemaUpdateComponent } from './form-schema-update.component';
import { FormSchemaDeleteDialogComponent } from './form-schema-delete-dialog.component';
import { formSchemaRoute } from './form-schema.route';

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(formSchemaRoute)],
  declarations: [FormSchemaComponent, FormSchemaDetailComponent, FormSchemaUpdateComponent, FormSchemaDeleteDialogComponent],
  entryComponents: [FormSchemaDeleteDialogComponent]
})
export class JhipsterSampleApplicationFormSchemaModule {}
