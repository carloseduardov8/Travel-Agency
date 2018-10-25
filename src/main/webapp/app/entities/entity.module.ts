import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ViajatoAeroportoModule } from './aeroporto/aeroporto.module';
import { ViajatoLinhaAereaModule } from './linha-aerea/linha-aerea.module';
import { ViajatoVooModule } from './voo/voo.module';
import { ViajatoLocadoraModule } from './locadora/locadora.module';
import { ViajatoVeiculoModule } from './veiculo/veiculo.module';
import { ViajatoLocacaoModule } from './locacao/locacao.module';
import { ViajatoHotelModule } from './hotel/hotel.module';
import { ViajatoQuartoModule } from './quarto/quarto.module';
import { ViajatoReservaModule } from './reserva/reserva.module';
import { ViajatoSeguradoraModule } from './seguradora/seguradora.module';
import { ViajatoContratoModule } from './contrato/contrato.module';
import { ViajatoCompraModule } from './compra/compra.module';
import { ViajatoPassagemModule } from './passagem/passagem.module';
import { ViajatoSeguroModule } from './seguro/seguro.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ViajatoAeroportoModule,
        ViajatoLinhaAereaModule,
        ViajatoVooModule,
        ViajatoLocadoraModule,
        ViajatoVeiculoModule,
        ViajatoLocacaoModule,
        ViajatoHotelModule,
        ViajatoQuartoModule,
        ViajatoReservaModule,
        ViajatoSeguradoraModule,
        ViajatoContratoModule,
        ViajatoCompraModule,
        ViajatoPassagemModule,
        ViajatoSeguroModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ViajatoEntityModule {}
