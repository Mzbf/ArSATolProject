import { NgModule } from '@angular/core';

import { ArsatollserviceSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [ArsatollserviceSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [ArsatollserviceSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class ArsatollserviceSharedCommonModule {}
