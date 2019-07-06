import {Entity, PrimaryGeneratedColumn, Column, OneToMany, ManyToOne, JoinColumn} from "typeorm";
import { User } from "./User";
import { Answer } from "./Answer";

@Entity({name: "questions"})
export class Question {

    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    description: string;

    @OneToMany(type => Answer, answer => answer.question)
    answers: Answer[]

    @ManyToOne(type => User, user => user.questions)
    @JoinColumn({name: "user_id"})
    user: User

}