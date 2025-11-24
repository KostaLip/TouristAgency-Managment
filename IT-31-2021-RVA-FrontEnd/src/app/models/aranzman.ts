import { Hotel } from "./hotel";
import { TuristickaAgencija } from "./turisticka_agencija";

export class Aranzman {
    id!: number;
    ukupnaCena!: number;
    placeno!: boolean;
    datumRealizacije!: Date;
    hotel!: Hotel;
    agencija!: TuristickaAgencija
}