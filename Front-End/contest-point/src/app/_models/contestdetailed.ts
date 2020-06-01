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
    userFirstName: string;
    userLastName: string;
    userEmail: string;
    userInstitution: string;
    locations: Location[];
    tags: Tag[];
    likes: Like[];
    requirements: Requirement[];
}