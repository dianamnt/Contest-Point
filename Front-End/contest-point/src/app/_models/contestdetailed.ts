import { Tag } from "./tag";
import {Location} from "./location";
import {ParticipationContract} from "./participationcontract";
import {Like} from "./like";
import {Requirement} from "./requirement";

export class ContestDetailed {
    contestId: number;
    contestName: string;
    details: string;
    partners: string;
    enrollmentStart: string;
    enrollmentDue: string;
    startDate: string;
    endDate: string;
    coverPicture: string;
    userId: number;
    locations: Location[];
    tags: Tag[];
    contracts: ParticipationContract[];
    likes: Like[];
    requirements: Requirement[];
}