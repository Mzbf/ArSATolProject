import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IDemo } from 'app/shared/model/demo.model';
import { DemoService } from './demo.service';

@Component({
    selector: 'jhi-demo-update',
    templateUrl: './demo-update.component.html'
})
export class DemoUpdateComponent implements OnInit {
    demo: IDemo;
    isSaving: boolean;

    constructor(protected demoService: DemoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ demo }) => {
            this.demo = demo;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.demo.id !== undefined) {
            this.subscribeToSaveResponse(this.demoService.update(this.demo));
        } else {
            this.subscribeToSaveResponse(this.demoService.create(this.demo));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemo>>) {
        result.subscribe((res: HttpResponse<IDemo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
