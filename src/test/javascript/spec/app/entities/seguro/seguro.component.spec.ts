/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ViajatoTestModule } from '../../../test.module';
import { SeguroComponent } from 'app/entities/seguro/seguro.component';
import { SeguroService } from 'app/entities/seguro/seguro.service';
import { Seguro } from 'app/shared/model/seguro.model';

describe('Component Tests', () => {
    describe('Seguro Management Component', () => {
        let comp: SeguroComponent;
        let fixture: ComponentFixture<SeguroComponent>;
        let service: SeguroService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ViajatoTestModule],
                declarations: [SeguroComponent],
                providers: []
            })
                .overrideTemplate(SeguroComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SeguroComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SeguroService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Seguro(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.seguros[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
