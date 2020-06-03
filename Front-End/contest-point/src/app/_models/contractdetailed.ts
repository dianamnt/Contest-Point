import {Detail} from "./detail";

export class ContractDetailed {
    pcId: number;
    userId: number;
    contestId:  number;
    userFirstName: string;
    userLastName: string;
    userEmail: string;
    details: Detail[];
}