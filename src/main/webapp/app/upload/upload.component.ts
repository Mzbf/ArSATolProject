import { UploadFileService } from './fileUpload.service';

import { Component } from '@angular/core';

import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';

@Component({
    selector: 'app-root',

    templateUrl: './upload.component.html',

    styleUrls: ['./app.component.css'],

    providers: [UploadFileService]
})
export class AppComponent {
    selectedFiles: FileList;

    currentFileUpload: File;

    constructor(private uploadService: UploadFileService) {}

    selectFile(event) {
        this.selectedFiles = event.target.files;
    }

    upload() {
        this.currentFileUpload = this.selectedFiles.item(0);

        this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
            if (event instanceof HttpResponse) {
                console.log('File is completely uploaded!');
            }
        });

        this.selectedFiles = undefined;
    }
}
