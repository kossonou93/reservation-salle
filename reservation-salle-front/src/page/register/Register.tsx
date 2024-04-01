import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {toast} from 'react-hot-toast';
import axios from "axios";
import "./register.css";
import type {Role} from "../../model/role.model";
import {getUriUser} from "../../UrlTools";
import {User} from "../../model/user.model";
import {useForm} from "react-hook-form";

function Register  ()   {
    const navigate = useNavigate()
    const [data, setData] = useState({
        email: '',
        username: '',
        contact: '',
        password: '',
        roles: [],
    });
    const [userForm, setForm] = useState<User>({
        id: undefined,
        version: undefined,
        email: "",
        username: "",
        password: "",
        contact: "",
        roles: []
    });

    const {handleSubmit} = useForm();

    function onSubmit(data: any): any{
        userForm.roles = [{"id":0, "version": 0, "name": "ROLE_USER"}];
        console.log("userForm ===> " + userForm.roles);
        axios.post(getUriUser(`enregistrer`), userForm).then((response) => {
            if (response.status===0){
                toast.success("Enregistrement éffectuer avec succès");
                navigate('/login');
            }else {
                toast.error("Une erreur s'est produite lors de l'enregistrement");
            }
        });
    }

    const loginAdmin = () => {
        //setData({});
        navigate('/admin');
    }

    return (
        <div>
            {/* TopBar Component */}
            <div className="login">
                <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="lContainer">
                                <input
                                type="text"
                                placeholder="Email"
                                value={userForm.email}
                                name={"email"}
                               onChange={(e: any) => {
                                   setForm(prevState => ({
                                       ...prevState,
                                       email: e.target.value,
                                   }));
                               }}
                                className="lInput"
                                />
                                <input
                                type="text"
                                placeholder="Username"
                                value={userForm.username}
                                name={"username"}
                                onChange={(e: any) => {
                                    setForm(prevState => ({
                                        ...prevState,
                                        username: e.target.value,
                                    }));
                                }}
                                className="lInput"
                                />
                                <input
                                type="text"
                                placeholder="Contact"
                                value={userForm.contact}
                                name={"contact"}
                                onChange={(e: any) => {
                                    setForm(prevState => ({
                                        ...prevState,
                                        contact: e.target.value,
                                    }));
                                }}
                                className="lInput"
                                />
                                <input
                                type="password"
                                placeholder="Password"
                                value={userForm.password}
                                name={"password"}
                                onChange={(e: any) => {
                                    setForm(prevState => ({
                                        ...prevState,
                                        password: e.target.value,
                                    }));
                                }}
                                className="lInput"
                                />
                                <button className="lButton">
                                    Register
                                </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
export default Register