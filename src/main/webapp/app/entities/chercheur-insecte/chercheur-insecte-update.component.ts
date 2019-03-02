import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IChercheurInsecte } from 'app/shared/model/chercheur-insecte.model';
import { ChercheurInsecteService } from './chercheur-insecte.service';

@Component({
    selector: 'jhi-chercheur-insecte-update',
    templateUrl: './chercheur-insecte-update.component.html'
})
export class ChercheurInsecteUpdateComponent implements OnInit {
    chercheurInsecte: IChercheurInsecte;
    isSaving: boolean;
    dateDAjout: string;
    dateValidation: string;

    constructor(protected chercheurInsecteService: ChercheurInsecteService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ chercheurInsecte }) => {
            this.chercheurInsecte = chercheurInsecte;
            this.dateDAjout = this.chercheurInsecte.dateDAjout != null ? this.chercheurInsecte.dateDAjout.format(DATE_TIME_FORMAT) : null;
            this.dateValidation =
                this.chercheurInsecte.dateValidation != null ? this.chercheurInsecte.dateValidation.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.chercheurInsecte.dateDAjout = this.dateDAjout != null ? moment(this.dateDAjout, DATE_TIME_FORMAT) : null;
        this.chercheurInsecte.dateValidation = this.dateValidation != null ? moment(this.dateValidation, DATE_TIME_FORMAT) : null;
        if (this.chercheurInsecte.id !== undefined) {
            this.subscribeToSaveResponse(this.chercheurInsecteService.update(this.chercheurInsecte));
        } else {
            this.subscribeToSaveResponse(this.chercheurInsecteService.create(this.chercheurInsecte));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IChercheurInsecte>>) {
        result.subscribe((res: HttpResponse<IChercheurInsecte>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
